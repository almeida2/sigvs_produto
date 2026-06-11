# Relatório de Métricas Arquiteturais
Análise de Acoplamento, Estabilidade e Abstração do padrão MVC + Service.

| Camada | $C_a$ | $C_e$ | Instabilidade ($I$) | Abstração ($A$) | Distância ($D$) |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **controller** | 0 | 2 | 1,00 | 0,00 | 0,00 |
| **service** | 1 | 2 | 0,67 | 0,33 | 0,00 |
| **repository** | 1 | 1 | 0,50 | 1,00 | 0,50 |
| **model** | 3 | 0 | 0,00 | 0,00 | 1,00 |

---
### Novas Métricas Adicionadas:
- **Abstração ($A$):** Proporção de classes abstratas e interfaces no pacote. Varia de 0 (apenas classes concretas) a 1 (apenas interfaces/abstratas).
- **Distância ($D$):** Distância linear até a Sequência Principal ($|A + I - 1|$). Quanto mais próximo de 0.00, mais equilibrado está o pacote. Valores próximos de 1.00 indicam a *Zona da Inutilidade* (muito abstrato e ninguém usa) ou a *Zona da Dor* (muito concreto e todo mundo depende dele).
