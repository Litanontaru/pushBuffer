package org;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class EntityInfo {
    private final Class domainClass;
    private final Object domainId;
}
