# Kubernetes tests
This project includes a very basic Spring Boot Application thats just for test purposes. 
The project gets dockerized and deployed in a K8s cluster. 
That's my test cluster for testing K8s services like ingress, deployments, configMaps, clusterRoles and much more.

I use helm with tiller on top of the kubernetes deployment files. 
This makes it easier to manage all different deployments.

### Installation Guide:

- Clone this repo
- Install Docker and start it
- Enable Kubernetes
- Install minikube(that is for pushing local docker images to your K8s registry)
- Run "mvn clean install" in the projects root folder
- Run "docker build ." to build a docker image of this project
- Make sure you are connected to the correct K8s cluster
- Run "helm install kubernetes-tests kubernetes-tests", 
  this will deploy the created docker image to your K8s cluster under default namespace "kubernetes-tests"
- Run "helm install kubernetes-tests-ingress kubernetes-tests-ingress" to create an ingress and route the spring boot app to _camillo.test_
- Add "127.0.0.1 camillo.test" to the hosts file of your local machine
- Visit [camillo.test](https://camillo.test/) in your browser(maybe you get a warning because no tls certificate is installed)
