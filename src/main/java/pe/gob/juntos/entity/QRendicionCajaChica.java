package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRendicionCajaChica is a Querydsl query type for RendicionCajaChica
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRendicionCajaChica extends EntityPathBase<RendicionCajaChica> {

    private static final long serialVersionUID = 757671399L;

    public static final QRendicionCajaChica rendicionCajaChica = new QRendicionCajaChica("rendicionCajaChica");

    public final StringPath anio = createString("anio");

    public final StringPath eliminado = createString("eliminado");

    public final StringPath estado = createString("estado");

    public final DateTimePath<java.util.Date> fechaApertura = createDateTime("fechaApertura", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaRendicionCajaChica = createDateTime("fechaRendicionCajaChica", java.util.Date.class);

    public final NumberPath<Long> idRendicionCajaChica = createNumber("idRendicionCajaChica", Long.class);

    public final NumberPath<Double> importe = createNumber("importe", Double.class);

    public final StringPath mes = createString("mes");

    public final StringPath usuarioApertura = createString("usuarioApertura");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath usuarioRendicionCajaChica = createString("usuarioRendicionCajaChica");

    public final StringPath vigente = createString("vigente");

    public QRendicionCajaChica(String variable) {
        super(RendicionCajaChica.class, forVariable(variable));
    }

    public QRendicionCajaChica(Path<? extends RendicionCajaChica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRendicionCajaChica(PathMetadata metadata) {
        super(RendicionCajaChica.class, metadata);
    }

}

