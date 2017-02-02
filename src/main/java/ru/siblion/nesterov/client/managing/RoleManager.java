package ru.siblion.nesterov.client.managing;

import ru.siblion.nesterov.client.type.Role;
import ru.siblion.nesterov.logreader.ws.LocationType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by alexander on 02.02.2017.
 */

/* Класс, который предоставляет управление ролями пользователя,
 * связывает возможность поиска в разных типах локациии и роли */
public class RoleManager {
    private Map<Role, Set<LocationType>> roles = new HashMap<>();;

    public RoleManager() {

        Set<LocationType> serverSearchRoleLocationTypes = new HashSet<>();
        serverSearchRoleLocationTypes.add(LocationType.SERVER);

        Set<LocationType> clusterSearchRoleLocationTypes = new HashSet<>();
        clusterSearchRoleLocationTypes.add(LocationType.SERVER);
        clusterSearchRoleLocationTypes.add(LocationType.CLUSTER);

        Set<LocationType> domainSearchRoleLocationTypes = new HashSet<>();
        domainSearchRoleLocationTypes.add(LocationType.SERVER);
        domainSearchRoleLocationTypes.add(LocationType.CLUSTER);
        domainSearchRoleLocationTypes.add(LocationType.DOMAIN);

        roles.put(Role.ServerSearchRole, serverSearchRoleLocationTypes);
        roles.put(Role.ClusterSearchRole, clusterSearchRoleLocationTypes);
        roles.put(Role.DomainSearchRole, domainSearchRoleLocationTypes);

    }

    public Map<Role, Set<LocationType>> getRoles() {
        return roles;
    }
}
