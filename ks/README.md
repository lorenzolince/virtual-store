# virtual-store ks
docker run -d -p 5000:5000 --name registry registry:latest
docker image tag spring-boot-app localhost:5000/spring-boot-app
docker push localhost:5000/spring-boot-app
kubectl apply -f