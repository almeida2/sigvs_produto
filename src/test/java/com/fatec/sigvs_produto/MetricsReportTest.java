package com.fatec.sigvs_produto;

import com.tngtech.archunit.core.domain.JavaClass;
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
import java.util.List;

/**
 * Classe para gerar relatório de métricas arquiteturais.
 * execute como um teste JUnit.
 * 
 * @author esai
 * @version 1.0
 */
public class MetricsReportTest {

        @Test
        public void gerarRelatorioAcoplamentoEAbstracaoMarkdown() throws IOException {
                // 1. Modifique para o pacote raiz do seu projeto
                String pacoteRaiz = "com.fatec.sigvs_produto";
                JavaClasses classes = new ClassFileImporter().importPackages(pacoteRaiz);

                // 2. Agrupa os componentes com base nas suas camadas MVC + Service
                MetricsComponents<JavaClass> componentes = MetricsComponents.fromPackages(List.of(
                                classes.getPackage(pacoteRaiz + ".controller"),
                                classes.getPackage(pacoteRaiz + ".service"),
                                classes.getPackage(pacoteRaiz + ".repository"),
                                classes.getPackage(pacoteRaiz + ".model")));

                // 3. Calcula as métricas completas de Robert C. Martin
                ComponentDependencyMetrics metricas = ArchitectureMetrics.componentDependencyMetrics(componentes);

                // 4. Caminho de saída
                String caminhoArquivo = Paths.get("metricas-arquitetura.md").toAbsolutePath().toString();

                // 5. Escrita do arquivo Markdown com Abstração (A) e Distância (D)
                try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
                        writer.println("# Relatório de Métricas Arquiteturais");
                        writer.println("Análise de Acoplamento, Estabilidade e Abstração do padrão MVC + Service.\n");
                        writer.println("| Camada | $C_a$ | $C_e$ | Instabilidade ($I$) | Abstração ($A$) | Distância ($D$) |");
                        writer.println("| :--- | :---: | :---: | :---: | :---: | :---: |");

                        for (var componente : componentes) {
                                String nomePacote = componente.getIdentifier().replace(pacoteRaiz + ".", "");
                                String idComp = componente.getIdentifier();

                                int ca = metricas.getAfferentCoupling(idComp);
                                int ce = metricas.getEfferentCoupling(idComp);
                                double instabilidade = metricas.getInstability(idComp);
                                // getAbstractness calcula a taxa de interfaces/classes abstratas
                                double abstracao = metricas.getAbstractness(idComp);
                                // getNormalizedDistanceFromMainSequence calcula a distância D
                                double distancia = metricas.getNormalizedDistanceFromMainSequence(idComp);

                                writer.printf("| **%s** | %d | %d | %.2f | %.2f | %.2f |\n",
                                                nomePacote, ca, ce, instabilidade, abstracao, distancia);
                        }

                        writer.println("\n---");
                        writer.println("### Novas Métricas Adicionadas:");
                        writer.println("- **Abstração ($A$):** Proporção de classes abstratas e interfaces no pacote. Varia de 0 (apenas classes concretas) a 1 (apenas interfaces/abstratas).");
                        writer.println("- **Distância ($D$):** Distância linear até a Sequência Principal ($|A + I - 1|$). Quanto mais próximo de 0.00, mais equilibrado está o pacote. Valores próximos de 1.00 indicam a *Zona da Inutilidade* (muito abstrato e ninguém usa) ou a *Zona da Dor* (muito concreto e todo mundo depende dele).");
                }

                System.out.println("Relatório completo gerado em: " + caminhoArquivo);
        }
}
