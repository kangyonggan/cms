package com.kangyonggan.cms.service;

import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.model.Dictionary;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/27/18
 */
public interface DictionaryService {

    /**
     * 搜索字典
     *
     * @param params
     * @return
     */
    List<Dictionary> searchDictionaries(Params params);

    /**
     * 保存字典
     *
     * @param dictionary
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 查找字典
     *
     * @param id
     * @return
     */
    Dictionary findDictionaryById(Long id);

    /**
     * 更新字典
     *
     * @param dictionary
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 删除字典
     *
     * @param id
     */
    void deleteDictionaryById(Long id);

    /**
     * 校验是否存在字典
     *
     * @param type
     * @param code
     * @return
     */
    boolean existsDictionary(String type, String code);
}
