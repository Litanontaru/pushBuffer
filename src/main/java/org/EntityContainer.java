package org;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class EntityContainer {
    private final Object dto;
    private final List<EntityInfo> isBlockedBy;
}
