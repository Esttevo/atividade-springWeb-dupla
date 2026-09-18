# Sistema API REST de controle do CRUD de Tarefas
Desenvolvido por Mateus e Christian nos dias 17/09/2026 e 18/09/2026. 

## Como utilizar
Baixe as dependecias que estão no pom.xml, e para acessar os endpoits
utilize o postman.

## Justificativas
Para guardar as tarefas como se fosse um banco, utilizamos uma lista static 
na camada de repository, a service nao funcionou como ela realmente deveria 
funcionar, lançando erros e tratando regras de negocio, tudo isso foi feito 
dentro da repository. Para o metodo de criar @PostMapping, o retorno do
status é 201, com responseEntity.created.body() com o obj criado. 
Para o metodo de Deletar, ele nao retorna corpo nenhum, somente o status 
ResponseEntity.noContent().build() para status 204. Nos outros metodos o
status de reposta é 200, junto com a solicitação do metodo.