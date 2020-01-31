package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCargo is a Querydsl query type for Cargo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCargo extends EntityPathBase<Cargo> {

    private static final long serialVersionUID = -293134147L;

    public static final QCargo cargo = new QCargo("cargo");

    public final StringPath codigoCargo = createString("codigoCargo");

    public final StringPath descripcionCargo = createString("descripcionCargo");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idCargo = createNumber("idCargo", Long.class);

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QCargo(String variable) {
        super(Cargo.class, forVariable(variable));
    }

    public QCargo(Path<? extends Cargo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCargo(PathMetadata metadata) {
        super(Cargo.class, metadata);
    }

}

