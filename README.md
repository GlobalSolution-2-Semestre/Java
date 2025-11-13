# MindJava API - Plataforma de Bem-Estar

Este projeto é o backend (API RESTful) da plataforma MindTrack. Ele foi construído em Java 17 e Quarkus para fornecer endpoints de alta performance para o gerenciamento de colaboradores, check-ins de humor, alertas e relatórios.

## 👩‍💻 Integrantes

* **Nome:** Guilherme Macedo Martins | **RM:** 562396 | **Turma:** 1TDSPF
* **Nome:** Pedro Henrique Luiz Alves Duarte | **RM:** 563405 | **Turma:** 1TDSPF

---

## 🚀 Instruções de Instalação e Execução

Este guia detalha os requisitos e os passos para configurar e executar a API.

### 1. Requisitos do Sistema

* **Java 17 (JDK)**
* **Apache Maven 3.9+**
* **Acesso à Rede da FIAP (Obrigatório)**

### 2. Configuração do Banco de Dados

**Nenhuma configuração é necessária.** O projeto está configurado para se conectar diretamente ao banco de dados Oracle da FIAP.

As credenciais estão definidas (hardcoded) na classe `br.com.mindjava.conexoes.ConexaoFactory` e **requerem que você esteja conectado à VPN da FIAP** para funcionar.

### 3. Como Rodar o Projeto

Existem duas formas de executar a aplicação (lembre-se de estar conectado à **VPN da FIAP**):

#### A) Modo de Desenvolvimento (Local)

Esta é a forma recomendada para testar. O Quarkus irá reiniciar automaticamente a cada mudança no código.

```bash
# Na raiz do projeto, execute:
./mvnw quarkus:dev
