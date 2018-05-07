package org;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityInfo {
    private final Class domainClass;
    private final Object domainId;
}
