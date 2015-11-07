/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.agilemavenplugin;

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
@Mojo(name = "parametros", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class Parametros extends AbstractMojo {

    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;
    
    @Parameter(property = "numero", defaultValue = "7")
    private String numero;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        PluginsUtil.assina();
        System.out.println("---------->" + project.getName() + " v:" + project.getVersion());
        int n=Integer.parseInt(numero);
        for (int i=1;i<10;i++){
            System.out.println(n+"x"+i+"="+(n*i));
        }
    }

}
