package dev.galliard.contasoc.util;

import com.sun.jna.platform.win32.Netapi32Util;
import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.*;
import dev.galliard.contasoc.database.objects.Gasto;
import dev.galliard.contasoc.database.objects.Ingreso;
import dev.galliard.contasoc.database.objects.Socio;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CodePlayground {
    private static Dao<Socio> jpaSocioDao = new JpaSocioDao();
    private static Dao<Ingreso> jpaIngresoDao = new JpaIngresoDao();
    private static Dao<Gasto> jpaGastoDao = new JpaGastoDao();

    public static void main(String[] args) throws SQLException {
        jpaGastoDao.save(
                new Gasto(
                        Date.valueOf(LocalDate.now()),
                        "Proveedor",
                        "Concepto",
                        100.0,
                        "Factura",
                        TipoPago.BANCO
                )
        );
    }
}

