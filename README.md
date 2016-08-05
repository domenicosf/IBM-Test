# IBM-Test

Para executar esta aplicação será necessário a instalação de um servidor de aplicação (Websphere Liberty ou Apache Tomcat) e um banco de dados relacional (MySQL) por exemplo. Recomenda-se que instale-se o seguinte servidor e banco de dados:

1 - Apache Tomcat - Servidor de aplicação;
2 - MySQL - Banco de dados relacional.

Ao instalar o MySQL mantenha as configurações padões como o endereço de acesso ao banco (localhost:3306). Após a instalação do MySQL recomenda-se a criação do banco de dados a ser usado para os testes. Este deve ter o nome de testdb. Finalmente criaremos a tabela user:
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `STATUS` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

Deve-se se instalar o servidor de aplicação e configurá-lo de maneira adequada com o framework Eclipse. Após configurado o projeto pode ser baixado através do git e importado como um projeto maven no Eclipse. Com isso o projeto pode ser executado e testado.

O projeto foi buildado com o Maven utilizando as opções clean e install.
