package com.ikolosov.apt.processor;

import com.ikolosov.apt.annotation.CustomAnnotation;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes("com.ikolosov.apt.annotation.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CustomAnnotationProcessor extends AbstractProcessor {

	public static final String VELOCITY_TEMPLATE = "/velocity.template.vm";

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			// [] annotated elements data
			TypeElement classElement = null;
			Map<String, ExecutableElement> methods = new HashMap<>();
			for (Element e : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
				if (ElementKind.CLASS.equals(e.getKind())) {
					classElement = (TypeElement) e;
				} else if (ElementKind.METHOD.equals(e.getKind())) {
					methods.put(e.getSimpleName().toString(), (ExecutableElement) e);
				}
			}
			// [] custom input data
			String wrapperClassSuffix = "Wrapper";
			String customMessage = "----- THIS IS WRAPPER'S CUSTOM MESSAGE -----";
			// [] velocity
			if (classElement != null) {
				// [] context
				VelocityContext vc = new VelocityContext();
				vc.put("packageName", ((PackageElement) classElement.getEnclosingElement()).getQualifiedName());
				vc.put("className", classElement.getSimpleName());
				vc.put("methods", methods);
				vc.put("wrapperClassSuffix", wrapperClassSuffix);
				vc.put("customMessage", customMessage);
				// [] engine
				VelocityEngine ve = new VelocityEngine();
				ve.setProperty("resource.loader", "classpath");
				ve.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
				ve.init();
				// [] source generation
				Writer writer = processingEnv.getFiler()
						.createSourceFile(classElement.getQualifiedName() + wrapperClassSuffix)
						.openWriter();
				ve.getTemplate(VELOCITY_TEMPLATE).merge(vc, writer);
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
