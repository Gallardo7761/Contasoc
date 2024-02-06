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
    private static final Dao<Socio> jpaSocioDao = new JpaSocioDao();
    private static final Dao<Ingreso> jpaIngresoDao = new JpaIngresoDao();
    private static final Dao<Gasto> jpaGastoDao = new JpaGastoDao();

    public static void main(String[] args) throws SQLException {
        jpaSocioDao.save(
                new Socio(
                        1,
                        1,
                        "Nombre",
                        "0000000T",
                        123456789,
                        null,
                        Date.valueOf(LocalDate.now()),
                        null,
                        null,
                        null,
                        TipoSocio.HORTELANO,
                        Estado.ACTIVO
                )
        );
        /*jpaGastoDao.save(
                new Gasto(
                        Date.valueOf(LocalDate.now()),
                        "Proveedor",
                        "Concepto",
                        100.0,
                        "Factura",
                        TipoPago.BANCO
                )
        );
        jpaIngresoDao.save(
                new Ingreso(
                        1,
                        Date.valueOf(LocalDate.now()),
                        "Concepto",
                        100.0,
                        TipoPago.BANCO
                )
        );*/
    }
}

