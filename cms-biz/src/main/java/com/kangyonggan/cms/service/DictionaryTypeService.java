package com.kangyonggan.cms.service;

import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.DictionaryType;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/27/18
 */
public interface DictionaryTypeService {

    /**
     * 搜索字典类型
     *
     * @param params
     * @return
     */
    List<DictionaryType> searchDictionaryTypes(Params params);

    /**
     * 保存字典类型
     *
     * @param dictionaryType
     */
    void saveDictionaryType(DictionaryType dictionaryType);

    /**
     * 更新字典类型
     *
     * @param dictionaryType
     */
    void updateDictionaryType(DictionaryType dictionaryType);

    /**
     * 查找字典类型
     *
     * @param id
     * @return
     */
    DictionaryType findDictionaryTypeById(Long id);

    /**
     * 删除字典类型
     *
     * @param id
     */
    void deleteDictionaryTypeById(Long id);

    /**
     * 校验是否存在字典类型
     *
     * @param type
     * @return
     */
    boolean existsDictionaryType(String type);

    /**
     * 查找所有字典类型
     *
     * @return
     */
    List<DictionaryType> findAllDictionaryTypes();

}
