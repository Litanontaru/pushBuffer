package org;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityInfo {
    private final Class entityClass;
    private final Object id;
}
