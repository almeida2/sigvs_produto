package com.fatec.test.java;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.metrics.ArchitectureMetrics;
import com.tngtech.archunit.library.metrics.ComponentDependencyMetrics;
import com.tngtech.archunit.library.metrics.MetricsComponents;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class MetricsReportTest {

    @Test
    public void gerarRelatorioAcoplamentoMarkdown() throws IOException {
        // 1. Modifique para o pacote raiz do seu projeto Spring Boot
        String pacoteRaiz = "com.seu.projeto";
        JavaClasses classes = new ClassFileImporter().importPackages(pacoteRaiz);

        // 2. Define os componentes com base nas suas camadas MVC + Service
        MetricsComponents<JavaClasses> componentes = MetricsComponents.fromPackages(
                classes.getPackage(pacoteRaiz + ".controller"),
                classes.getPackage(pacoteRaiz + ".service"),
                classes.getPackage(pacoteRaiz + ".repository"),
                classes.getPackage(pacoteRaiz + ".model")
        );

        // 3. Calcula as métricas de Robert C. Martin
        ComponentDependencyMetrics metricas = ArchitectureMetrics.componentDependencyMetrics(componentes);

        // 4. Caminho de saída (Raiz do projeto)
        String caminhoArquivo = Paths.get("metricas-acoplamento.md").toAbsolutePath().toString();

        // 5. Escrita do arquivo Markdown
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            writer.println("# Relatório de Métricas de Acoplamento");
            writer.println("Métricas baseadas em componentes (pacotes) do padrão MVC + Service.\n");
            writer.println("| Pacote / Camada | Acoplamento Aferente ($C_a$) | Acoplamento Eferente ($C_e$) | Instabilidade ($I$) |");
            writer.println("| :--- | :---: | :---: | :---: |");

            for (var componente : componentes) {
                String nomePacote = componente.getIdentifier().replace(pacoteRaiz + ".", "");
                
                int ca = metricas.getAfferentCoupling(componente.getIdentifier());
                int ce = metricas.getEferentCoupling(componente.getIdentifier());
                double instabilidade = metricas.getInstability(componente.getIdentifier());

                writer.printf("| **%s** | %d | %d | %.2f |\n", nomePacote, ca, ce, instabilidade);
            }
            
            writer.println("\n---");
            writer.println("### Legenda dos conceitos:");
            writer.println("- **Acoplamento Aferente ($C_a$):** Quantas classes de *fora* dependem deste pacote (indica responsabilidade).");
            writer.println("- **Acoplamento Eferente ($C_e$):** De quantas classes de *fora* este pacote depende (indica dependência).");
            writer.println("- **Instabilidade ($I$):** Razão $C_e / (C_a + C_e)$. Varia de 0 (totalmente estável/difícil de mudar) a 1 (totalmente instável).");
        }
        
        System.out.println("Relatório gerado com sucesso em: " + caminhoArquivo);
    }
}
