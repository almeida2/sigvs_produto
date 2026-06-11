# Relatório de Métricas de Acoplamento
Métricas baseadas em componentes (pacotes) do padrão MVC + Service.

| Pacote / Camada | Acoplamento Aferente ($C_a$) | Acoplamento Eferente ($C_e$) | Instabilidade ($I$) |
| :--- | :---: | :---: | :---: |
| **controller** | 0 | 2 | 1,00 |
| **service** | 1 | 2 | 0,67 |
| **repository** | 1 | 1 | 0,50 |
| **model** | 3 | 0 | 0,00 |

---
### Legenda dos conceitos:
- **Acoplamento Aferente ($C_a$):** Quantas classes de *fora* dependem deste pacote (indica responsabilidade).
- **Acoplamento Eferente ($C_e$):** De quantas classes de *fora* este pacote depende (indica dependência).
- **Instabilidade ($I$):** Razão $C_e / (C_a + C_e)$. Varia de 0 (totalmente estável/difícil de mudar) a 1 (totalmente instável).
