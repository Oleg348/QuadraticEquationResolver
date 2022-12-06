### Get запрос решения уравнения с json параметром с коэффициентами a,b,c:

http://localhost:8080/equation-resolver/resolve-json?params=%7B%22a%22:1%2C%22b%22:4%2C%22c%22:4%7D

### Get запрос решения уравнения с коэффициентами a,b,c:

http://localhost:8080/equation-resolver/resolve?a=1&b=-4&c=4

### Get запрос последних решенных уравнений:

http://localhost:8080/equation-resolver/last-solutions?amount=10

### Данные хранятся в db H2. Доступ:
http://localhost:8080/h2-console/  
jdbc.url=jdbc:h2:mem:db  
username=sa
