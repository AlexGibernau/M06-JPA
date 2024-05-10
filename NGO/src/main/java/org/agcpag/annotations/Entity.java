package org.agcpag.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    String name() default "";

   Class<? extends EntityNameValidator> nameValidator() default DefaultEntityNameValidator.class;
   /* Las anotaciones en Java no pueden tener métodos con cuerpo. Solo pueden tener métodos sin cuerpo, ya que son solo definiciones de metadatos. Sin embargo, puedes definir métodos que actúen como funciones dentro de una interfaz y luego implementar esa interfaz en tu clase anotación.*/
}
