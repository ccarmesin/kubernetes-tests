## Order of execution

Navigate to _/kubernetes/ingress/controller_ <br>
The names of the different steps match the names of .yaml files inside the filesystem.

<br>
<br>

#### 1. namespace

Create a different namespace just for ingress

#### 2. service-account

Create an account for ingress that we will limit in its power in the further steps

#### 3. cluster-role

Create a role for the service account from step 2 to limit its access rights

#### 4. cluster-role-binding

Bind the role from step 3 to the ingress service account from step 2

#### 5. configMap
#### 6. custom-snippets.configmap
#### 7. deployment
#### 8. service
#### 9. tls-secret

<br>
<br>

Navigate to _/kubernetes/ingress_ <br>

#### 10. ingress-nginx-example

Finally define the paths were you want your app to be available

---
## Tips:

Maybe you have to configure your DNS to route your request to your localhost.
<br>
<br>
Under Windows you have to navigate to _C:\Windows\System32\drivers\etc_ and add the following line to the _hosts_ file:

    127.0.0.1 camillo.test