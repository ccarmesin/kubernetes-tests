# Kubernetes tests
This project includes a very basic Spring Boot Application thats just for test purposes. 
The project gets dockerized and deployed in a K8s cluster. 
That's my test cluster for testing K8s services like ingress, deployments, configMaps, clusterRoles and much more.

I use helm with tiller on top of the kubernetes deployment files. 
This makes it easier to manage all different deployments.

---

## Installation Guide:

#### Load the Helm chart for Nginx Ingress

```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
```

#### Install the Helm (v3) chart for nginx ingress controller
If using Bash instead of Powershell, replace ` with \

```
helm install app-ingress ingress-nginx/ingress-nginx \
     --namespace ingress \
     --create-namespace \
     --set controller.replicaCount=2 \
     --set controller.nodeSelector."beta\.kubernetes\.io/os"=linux \
     --set defaultBackend.nodeSelector."beta\.kubernetes\.io/os"=linux
```


#### Create Namespace for demo app
```
kubectl apply -f app-namespace.yaml
```

#### Deploy demo app and service
```
kubectl apply -f app-deployment.yaml
```

#### Add ingress
```
kubectl apply -f app-ingress.yaml
```

#### Test your app
1. Add "127.0.0.1 camillo.test" to the hosts file of your local machine(On Windows: C:\Windows\System32\drivers\etc)
2. Visit [camillo.test](https://camillo.test/) in your browser

---
#### Second Part
Now we can start installing a cert-manager to get SSL/TLS encryption on our website. If you test your cluster on localhost it will be easier to create your own certificate, selfsign it and add it to your trust files.


#### Create a namespace for Cert Manager
```
kubectl create namespace cert-manager
```

#### Get the Helm Chart for Cert Manager
```
helm repo add jetstack https://charts.jetstack.io
helm repo update
```

#### Install Cert Manager using Helm charts
If using Bash instead of Powershell, replace ` with \
```
helm install cert-manager jetstack/cert-manager \
    --namespace cert-manager \
    --version v0.14.0 \
    --set installCRDs=true
```

#### Check the created Pods
```
kubectl get pods --namespace cert-manager
```
#### Apply jetstack extension to be able to execute the next steps without error
```
kubectl apply -n cert-manager--validate=false -f https://github.com/jetstack/cert-manager/releases/download/v0.15.1/cert-manager.crds.yaml
```
#### Install the Cluster Issuer
```
kubectl apply --namespace app -f ssl-tls-cluster-issuer.yaml
```
#### Install the Ingress resource configured with TLS/SSL
```
kubectl apply --namespace app -f ssl-tls-ingress.yaml
```
#### Verify that the certificate was issued
```
kubectl describe cert app-web-cert --namespace app
```
#### Check the services
```
kubectl get services -n app
```
