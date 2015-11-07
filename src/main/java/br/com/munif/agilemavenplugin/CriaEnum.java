/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.agilemavenplugin;

import java.io.File;
import java.io.FileWriter;
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
@Mojo(name = "enum", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class CriaEnum extends AbstractMojo {

    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "nome", defaultValue = "NovoEnum")
    private String nome;

    @Parameter(property = "valores", defaultValue = "A,B,C")
    private String valores;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        String nomePacote = nome.substring(0, nome.lastIndexOf('.'));
        String nomeEnum = nome.substring(nome.lastIndexOf('.') + 1);
        String pastaEnum = PluginsUtil.windowsSafe(project.getCompileSourceRoots().get(0)) + "/".concat(nomePacote.replaceAll("\\.", "/"));

        getLog().info("Iniciando plugin Gerador de Enum GUMGA ");
        getLog().info("Gerando " + nomePacote + "." + nomeEnum);
        File f = new File(pastaEnum);
        f.mkdirs();
        File arquivoClasse = new File(pastaEnum + "/" + nomeEnum + ".java");

        try {
            FileWriter fw = new FileWriter(arquivoClasse);
            fw.write("package " + nomePacote + ";\n\n");

            fw.write("/**\n"
                    + " *\n"
                    + " * @author agile\n"
                    + " */\n"
                    + "public enum " + nomeEnum + " {\n\n");
            fw.write(valores);
            fw.write("\n}\n");
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
