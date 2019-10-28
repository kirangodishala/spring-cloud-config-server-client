### SpringBoot dynamic refresh taking long time  

This is a simple demo to highlight the unbelievable long time SpringBoot takes to refresh the dynamic configuration properties when the number of properties is large.


#### Setup:
There are two modules : config-server and config-client.
config-server runs on port 8888 and client on port 8080.

K8sRestController is defined with @RefreshScope and has K8sProperties @Autowired. 

K8sProperties is annotated with @ConfigurationProperties and the source of the properties is a git repository configured using spring.cloud.config.server.git.uri property.

#### Steps to reproduce the issue: 
Upload **clouddriver.yml** file which has around 12000 properties related to 400 **ManagedAccount**s. A sample file with one ManagedAccount looks like the following:
```
kubernetes:
  enabled: true
  accounts:
  - name: k8s-acc-1
    liveManifestCalls: true
    requiredGroupMembership: []
    providerVersion: V2
    permissions: {}
    dockerRegistries: []
    configureImagePullSecrets: true
    cacheThreads: 1
    metrics: false
    namespaces:
      - ns1
      - ns2
    omitNamespaces: []
    kinds: []
    omitKinds: 
    - clusterRoleBinding
    - controllerRevision
    - customResourceDefinition
    - role
    - roleBinding
    - persistentVolume
    - apiService
    - validatingWebhookConfiguration
    - podPreset
    - mutatingWebhookConfiguration
    - podSecurityPolicy
    customResources: []
    cachingPolicies: []
    kubeconfigFile: /home/spinnaker/.hal/default/staging/dependencies/1573052617-config
    checkPermissionsOnStartup: false
    oAuthScopes: []
    onlySpinnakerManaged: false
```    
1. Run the config-server module.
2. Run the config-client module.  Its taking around **7min** for the client application to be up and running.
3. Now without even updating the clouddriver.yml, just triggering the /refresh end point takes again around **7min** to throw an empty array indicating no changes in the properties.
curl command: curl -X POST http://localhost:8080/actuator/refresh


 