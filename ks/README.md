# virtual-store ks
docker run -d -p 5000:5000 --name registry registry:latest
docker image tag spring-boot-app localhost:5000/spring-boot-app
docker push localhost:5000/spring-boot-app
docker image tag sql-server-store localhost:5000/sql-server-store
docker push localhost:5000/sql-server-store
docker image tag store-app localhost:5000/store-app
docker push localhost:5000/store-app
kubectl apply -f ks