package com.ytrue.yadmin.common.base;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/12 13:49
 * @description mapStruct基础类
 */
public interface BaseMapper<D, E> {

    /**
     * DTO转Entity
     *
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity /
     * @return /
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList /
     * @return /
     */
    List<D> toDto(List<E> entityList);
}
