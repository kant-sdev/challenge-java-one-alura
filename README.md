
# 💰 DevBank

Um banco digital interativo desenvolvido em Java, executado via terminal, com suporte a investimentos em múltiplas moedas, conversões cambiais em tempo real via ExchangeRate-API e interface textual totalmente formatada.

> 🚀 Projeto educacional - Challenge Conversor de Moedas da Alura - parte da formação Java Orientação a Objetos. Desenvolvido com foco em prática de API, POO e manipulação de dados JSON no back-end Java.
---
## 🧭 Visão Geral

O **DevBank** simula operações bancárias simples, permitindo que o usuário:

- 🧾 Crie sua conta digital com nome, saldo e país de origem  
- 💹 Faça **investimentos** em moedas estrangeiras (USD, EUR, GBP...)  
- 🔁 Faça **resgates** de volta para o Real (BRL)  
- 🌍 Use **conversões personalizadas** entre diferentes moedas  
- 📊 Visualize informações da conta e investimentos  
- 🧠 Tenha suporte automático via **API de câmbio** com **fallback offline**  

---

## ⚙️ Estrutura do Projeto

```
├── 📁 src
│   └── 📁 br
│       └── 📁 com
│           └── 📁 devbank
│               ├── 📁 actions
│               │   └── ☕ UsuarioIntecacao.java
│               ├── 📁 menus
│               │   ├── ☕ MenuInformacoes.java
│               │   ├── ☕ MenuInvestimento.java
│               │   └── ☕ MenuPrincipal.java
│               ├── 📁 models
│               │   ├── ☕ Conta.java
│               │   └── ☕ Investimentos.java
│               ├── 📁 services
│               │   └── ☕ ApiService.java
│               ├── 📁 tests
│               │   └── ☕ TesteApi.java
│               └── ☕ DevBank.java
├── ⚙️ .gitignore
├── 🔑 .env
├── 📝 README.md
└── 📄 challenge-java-one-alura.iml
```

## 🧩 Funcionalidades

### 🏦 Conta Digital
Criação de conta com nome, saldo inicial e país de origem.

```java
Conta minhaConta = new Conta("Icarus", 1500, "Brasil");
System.out.println(minhaConta.getInfoGeralConta());
```

### 💹 Investimentos
Menus de investimento e resgate com 6 opções fixas + 1 personalizada:
```
[1] Investir: BRL ➜ USD
[2] Investir: BRL ➜ EUR
[3] Investir: BRL ➜ GBP
[4] Resgatar: USD ➜ BRL
[5] Resgatar: EUR ➜ BRL
[6] Resgatar: GBP ➜ BRL
[7] Conversão personalizada 🌍
[8] Voltar
```

### 🌍 API de Câmbio
As taxas são obtidas através de uma **API real de câmbio**.
Em caso de falha, o sistema usa **valores simulados** (`getTaxaFallback()`).

### 📊 Menu de Informações
Permite visualizar dados da conta e lista de investimentos realizados.

---

## 🧠 Tecnologias Utilizadas

| Tecnologia | Descrição/Uso |
|-------------|-----|
| ☕ Java 21+ | Linguagem principal do projeto, aproveitando recursos modernos da linguagem (como text blocks e switch expressions). |
| 🌐 HttpClient(Java.net) | Utilizado para realizar chamadas HTTP e obter dados de conversão de moedas em tempo real. |
| 🧩 Gson | Biblioteca Google usada para fazer o parsing de respostas JSON recebidas da API de câmbio. |
| 🗝️ Java-dotenv | Responsável por carregar variáveis de ambiente a partir do arquivo .env, como a chave da API de câmbio. |
| 💾 Collections (List, Map) | Para gerenciar dados como contas e investimentos em memória de forma organizada. |
| 🎨 Text Blocks e Emojis| Melhoram a legibilidade e experiência do usuário no terminal. |
| 🗝️ Java-dotenv | Para leitura de variaveis de ambiente |

---

## 🗝️ Variáveis de Ambiente (.env)

Crie um arquivo `.env` na raiz do projeto:

```bash
API_KEY=your_api_key_here
API_URL=https://v6.exchangerate-api.com/v6/
```

> 🔒 **Nunca exponha suas chaves públicas!** Adicione o `.env` ao seu `.gitignore`.

---

## 🏁 Execução

Compile e execute o projeto com:

```bash
javac -d out src/br/com/devbank/**/*.java
java -cp out br.com.devbank.Main
```

> Ou dê run na pela IDE que estiver usando

---

## 🌟 Demonstração (exemplo de fluxo)

```
======= 💰 DEVBANK 💰 =======
Bem-vindo(a) ao seu banco digital!

[1] Criar conta
[2] Investimentos
[3] Ver Informações
[4] Sair

Informe sua escolha: 1
Informe seu nome: Icarus
Informe seu saldo: 1500
Informe seu país: Brasil

Conta criada com sucesso! ✅
Conta de Icarus | Saldo Atual: 1500.00 | País: Brasil

====== 💹 MENU DE INVESTIMENTOS ======

[1] Investir: Real (BRL) ➜ Dólar (USD)
[2] Investir: Real (BRL) ➜ Euro (EUR)
[3] Investir: Real (BRL) ➜ Libra (GBP)
[4] Resgatar: Dólar (USD) ➜ Real (BRL)
[5] Resgatar: Euro (EUR) ➜ Real (BRL)
[6] Resgatar: Libra (GBP) ➜ Real (BRL)
[7] Fazer conversão personalizada 🌎
[8] Voltar ao menu principal

Opção: 1
💰 Informe o valor a investir: 500

✅ Investimento realizado com sucesso!

💱 Conversão:
500 BRL ➜ 92.15 USD (Taxa: 5.43)

💼 Saldo atual da conta: 1000.00 BRL

====================================
        📊 MENU DE INFORMAÇÕES
====================================

Escolha o que deseja visualizar:

[1] Ver informações da conta 💼
[2] Ver todos os investimentos 📈
[3] Ver investimento específico 🔍
[4] Voltar ao menu principal ↩️
------------------------------------
```

---

## 💡 Autor

Desenvolvido por **Kant-sdev** 
   
📚 Projeto educacional inspirado nos desafios da **Oracle Next Education (ONE)** em parceria com a **Alura**.

*[Linkedin](www.linkedin.com/in/kauã-cantanhêde)*

---

## 🛡️ Licença

Projeto desenvolvido para fins didáticos, com o objetivo de estudo e prática de conceitos de Java e integração com APIs.

