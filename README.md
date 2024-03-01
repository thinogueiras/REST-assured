# Testes de API com REST-assured e JUnit 5.

## Pré-requisitos mínimos de ambiente:

* [Java](https://www.oracle.com/br/java/technologies/downloads/#java11) 11.0.x.

## IDE de desenvolvimento:

* [Eclipse](https://eclipseide.org).

## Clone do repositório:

```
git clone https://github.com/thinogueiras/REST-assured.git
```

## Abrir o projeto no Eclipse:

* Abra o Eclipse >> Menu File >> Import >> Maven >> Existing Maven Projects.
* Navegar até o repositório clonado >> Selecionar a pasta >> Finish.

## Execução dos testes:

### JUnit:

* Após importar o projeto com sucesso no Eclipse, clique para expandir o Source Folder: `src/test/java`, clique com o botão `direito` do mouse sobre o package: `br.qa.thinogueiras.suite` >> Run As >> JUnit Test.

### Terminal:

``` 
mvn clean test
```

## Relatório:
* #### Abrir o terminal e digitar:

  ```
  allure serve allure-results
  ```

![Alt text](/.github/images/allure-results.png)

---

<a href="https://www.linkedin.com/in/thinogueiras"><img alt="Linkedin" src="https://img.shields.io/badge/-LinkedIn-blue?style=for-the-badge&logo=Linkedin&logoColor=white"></a>

<strong>Thiago Nogueira dos Santos</strong> 🤓 ✌🏻

QA Automation Engineer 🔎 🐞
