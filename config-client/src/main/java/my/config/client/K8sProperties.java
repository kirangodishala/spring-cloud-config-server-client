package my.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "kubernetes")
public class K8sProperties {
  private List<ManagedAccount> accounts = new ArrayList<>();

  @Data
  public static class ManagedAccount {
    private String name;
    private ProviderVersion providerVersion;
    private String environment;
    private String accountType;
    private String context;
    private String cluster;
    private String oAuthServiceAccount;
    private List<String> oAuthScopes;
    private String user;
    private String kubeconfigFile;
    private String kubeconfigContents;
    private String kubectlExecutable;
    private Integer kubectlRequestTimeoutSeconds;
    private boolean serviceAccount = false;
    private boolean configureImagePullSecrets = true;
    private List<String> namespaces = new ArrayList<>();
    private List<String> omitNamespaces = new ArrayList<>();
    private String skin;
    private int cacheThreads;
    private List<String> requiredGroupMembership = new ArrayList<>();
    private String namingStrategy = "kubernetesAnnotations";
    private boolean debug = false;
    private boolean metrics = true;
    private boolean checkPermissionsOnStartup = true;
    private List<String> kinds = new ArrayList<>();
    private List<String> omitKinds = new ArrayList<>();
    private boolean onlySpinnakerManaged = false;
    private boolean liveManifestCalls = false;
    private Long cacheIntervalSeconds;
  }
}
