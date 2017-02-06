package ru.siblion.nesterov.client.managing;

import ru.siblion.nesterov.client.type.Role;
import ru.siblion.nesterov.logreader.ws.LocationType;

import java.util.*;

/**
 * Created by alexander on 02.02.2017.
 */

/* Класс, который предоставляет управление ролями пользователя,
 * связывает возможность поиска в разных типах локациии и роли */
public class RoleManager {
    private Map<Role, Set<LocationType>> roles = new HashMap<>();;

    public RoleManager() {
        /* ws-import импортит класс LocationType, при этом константы записываются большими буквами.
        *  Надо либо руками исправлять на маленькие буквы, либо в основном проекте записать их большими */

        Set<LocationType> serverSearchRoleLocationTypes = new LinkedHashSet<>();
        serverSearchRoleLocationTypes.add(LocationType.SERVER);

        Set<LocationType> clusterSearchRoleLocationTypes = new LinkedHashSet<>();
        clusterSearchRoleLocationTypes.add(LocationType.SERVER);
        clusterSearchRoleLocationTypes.add(LocationType.CLUSTER);

        Set<LocationType> domainSearchRoleLocationTypes = new LinkedHashSet<>();
        domainSearchRoleLocationTypes.add(LocationType.SERVER);
        domainSearchRoleLocationTypes.add(LocationType.CLUSTER);
        domainSearchRoleLocationTypes.add(LocationType.DOMAIN);

        Set<LocationType> AdminLocationTypes = new LinkedHashSet<>();
        AdminLocationTypes.add(LocationType.SERVER);
        AdminLocationTypes.add(LocationType.CLUSTER);
        AdminLocationTypes.add(LocationType.DOMAIN);

        roles.put(Role.ServerSearchRole, serverSearchRoleLocationTypes);
        roles.put(Role.ClusterSearchRole, clusterSearchRoleLocationTypes);
        roles.put(Role.DomainSearchRole, domainSearchRoleLocationTypes);
        roles.put(Role.Admin, AdminLocationTypes);

    }

    public Map<Role, Set<LocationType>> getRoles() {
        return roles;
    }
}
