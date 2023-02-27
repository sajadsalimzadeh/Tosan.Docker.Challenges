
# Docker Challenges Level 01

Java is use for docker challenge samples

- [x]  Author endpoint
- [x]  Hello endpoint with {name} paramerter
- [x]  Unit Testing by JUnit
- [x]  Logging with logback to console and file
- [x]  Containerize by docker from openjdk-18

## Authors

- [@sajadsalimzadeh](https://www.github.com/sajadsalimzadeh)


## Java Requirements
- JDK 17

## Docker Requirements

- Docker Engine
- Novin Repository Access
- Add novinrepo to "insecureregisteries"

## Packageing and Build Image

Packaging manually

```bash
mvn clean package
docker build . --tag challenge-levelx
```

Packaging with package file
```bash
run package.bat
```

## Run
to run project has these ways:
- Run war directly in target directory
- Run container from created image
- Run run.bat file

Run war file directly
```bash
java -jar target/challenge.war
```

```bash
docker run -p 8080:9001 -e DOCKER_CHALLENGES_SERVER_PORT=9001 challenge-levelx
```
## Options and Environment Variables

if run directly with war file you can set -D flag with server.port
```
java -jar -Dserver.port=4815 challenge.war
```

To run this project, you will need to add the following environment variables to your .env file or run container by -e flag

`DOCKER_CHALLENGES_SERVER_PORT` Default value 8080


## Usage/Examples


```
http://localhost:8080/author  => 'Sajad Salimzadeh'
http://localhost:8080/hello => 'Hello Stranger'
http://localhost:8080/hello?name=sajadSalimzadeh => 'sajad Salimzadeh'
http://localhost:8080/hello?name=aliGolMahammadi%20azariAsl => 'ali Gol Mahammadi Azari Asl'
```
Good luck 👍

# Docker Challenges Level 02
In this Challenge we want's to create cluster with kind kubernetes then apply deployment and service with 3 replicas

## Requirements
You must build target image to local if not pushed in registry. (image: challenge-levelx:latest) 

## Create Cluster
run command below in level02 directory
```
kind.exe create cluster --config ./cluster.yaml --image novinrepo:8082/docker/kindest/node:v1.25.3
```
Response
```
Creating cluster "kind" ...
 • Ensuring node image (novinrepo:8082/docker/kindest/node:v1.25.3) 🖼  ...
 ✓ Ensuring node image (novinrepo:8082/docker/kindest/node:v1.25.3) 🖼
 • Preparing nodes 📦   ...
 ✓ Preparing nodes 📦 
 • Writing configuration 📜  ...
 ✓ Writing configuration 📜
 • Starting control-plane 🕹️  ...
 ✓ Starting control-plane 🕹️
 • Installing CNI 🔌  ...
 ✓ Installing CNI 🔌
 • Installing StorageClass 💾  ...
 ✓ Installing StorageClass 💾
Set kubectl context to "kind-kind"
You can now use your cluster with:

kubectl cluster-info --context kind-kind

Have a question, bug, or feature request? Let us know! https://kind.sigs.k8s.io/#community 🙂
```
image prefix novinrepo:8082 used for local registry if you want use dockerhub remove --image option.  
To check cluster mapped port test localhost:8090 (can change it inside cluster.yaml)

## Load image into node
Now we have image in local, to make it resolvable in cluster must load image into it with command below.
```
kind.exe load docker-image challenge-levelx:latest
```
Response
```
Image: "" with ID "sha256:cd189f2d644c44769589eafd7336be12e487f88f5320e8006c9cd01f016d2e91" not yet present on node "kind-control-plane", loading...
```

## Apply Deployment Configuration
We use deployment to manage multiple replicas automatically by kubernetes
```
kubectl.exe apply -f .\deployment.yaml
```
Response
```
deployment.apps/challenge-deployment created
```

## Apply Service Configuration
For load balancing we can use service to forward our request to mapped pods with selector
```
kubectl.exe apply -f .\service.yaml
```
Response
```
service/challenge-service created
```

## Check Deployments and pods and service
Wait a bit then Check It
```
kubectl.exe get deployments
```
Response
```
NAME                   READY   UP-TO-DATE   AVAILABLE   AGE
challenge-deployment   1/1     1            1           70s
```
Check Pods:
```
kubectl.exe get pods
```
Response
```
NAME                                    READY   STATUS    RESTARTS   AGE
challenge-deployment-555d96d6bb-4lbf6   1/1     Running   0          37s
```
Check Service:
```
kubectl.exe get services
```
Response
```
NAME         TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes   ClusterIP   10.96.0.1       <none>        443/TCP          3m31s
challenge-service   NodePort    10.96.213.132   <none>        8090:31247/TCP   2m3s
```

## Test
Now it's ready to test
```
http://localhost:8090/author
```