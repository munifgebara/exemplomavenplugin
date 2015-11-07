/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.agilemavenplugin;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 *
 * @author munif
 */
@Mojo(name = "grafico", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class Reflexao extends AbstractMojo {

    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "nome", defaultValue = "Classe")
    private String nome;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String nomeClasse = nome.substring(nome.lastIndexOf('.') + 1);

        try {
            Class classe = PluginsUtil.getClassLoader(project).loadClass(nome);
            Field atributos[] = classe.getDeclaredFields();

            File arquivoClasse = new File(nomeClasse + ".txt");

            FileWriter fw = new FileWriter(arquivoClasse);

            fw.write(""
                    + "digraph G{\n"
                    + "   " + nomeClasse + " -> {\n");
            for (Field atributo : atributos) {
                fw.write(atributo.getName() + " ");
            }

            fw.write("}\n");

            for (Field atributo : atributos) {
                fw.write(atributo.getName() + " -> " + atributo.getType().getSimpleName()+"\n");
            }

            fw.write("}");
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
