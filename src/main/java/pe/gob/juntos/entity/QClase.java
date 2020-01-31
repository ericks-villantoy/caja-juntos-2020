package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClase is a Querydsl query type for Clase
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClase extends EntityPathBase<Clase> {

    private static final long serialVersionUID = -292822421L;

    public static final QClase clase = new QClase("clase");

    public final StringPath codigoClase = createString("codigoClase");

    public final StringPath descripcionClase = createString("descripcionClase");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idClase = createNumber("idClase", Long.class);

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QClase(String variable) {
        super(Clase.class, forVariable(variable));
    }

    public QClase(Path<? extends Clase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClase(PathMetadata metadata) {
        super(Clase.class, metadata);
    }

}

