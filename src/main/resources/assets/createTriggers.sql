CREATE TRIGGER IF NOT EXISTS tr_IncrNumeroSocio
    AFTER INSERT ON Socios
BEGIN
    UPDATE Socios
    SET numeroSocio = COALESCE((SELECT MAX(numeroSocio) FROM Socios), 0) + 1
    WHERE idSocio = NEW.idSocio AND NEW.numeroSocio IS NULL;
END;

CREATE TRIGGER IF NOT EXISTS tr_SetEstadoActivo
    AFTER INSERT ON Socios
BEGIN
    UPDATE Socios SET estado = 'ACTIVO'
    WHERE idSocio = NEW.idSocio;
END;

CREATE TRIGGER IF NOT EXISTS tr_SetEstadoInactivo
    AFTER UPDATE ON Socios
BEGIN
    UPDATE Socios SET estado = 'INACTIVO'
    WHERE idSocio = NEW.idSocio AND NEW.fechaDeBaja IS NOT NULL;
END;