package com.kangyonggan.cms.controller.dashboard.system;

import com.kangyonggan.cms.annotation.Token;
import com.kangyonggan.cms.controller.BaseController;
import com.kangyonggan.cms.dto.Page;
import com.kangyonggan.cms.dto.Params;
import com.kangyonggan.cms.dto.Response;
import com.kangyonggan.cms.model.DictionaryType;
import com.kangyonggan.cms.service.DictionaryTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @date 2017/1/9
 */
@Controller
@RequestMapping("dashboard/system/dict/type")
public class DashboardSystemDictTypeController extends BaseController {

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 字典类型管理
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    public String index() {
        return getPathList();
    }

    /**
     * 字典类型列表查询
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Page<DictionaryType> list() {
        Params params = getRequestParams();
        List<DictionaryType> dictionaryTypes = dictionaryTypeService.searchDictionaryTypes(params);

        return new Page<>(dictionaryTypes);
    }

    /**
     * 添加字典类型
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "createDictType")
    public String create(Model model) {
        model.addAttribute("dictionaryType", new DictionaryType());
        return getPathFormModal();
    }

    /**
     * 保存字典类型
     *
     * @param dictionaryType
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "createDictType", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("dictionaryType") @Valid DictionaryType dictionaryType, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryTypeService.saveDictionaryType(dictionaryType);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 编辑字典类型
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "editDictType")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dictionaryType", dictionaryTypeService.findDictionaryTypeById(id));
        return getPathFormModal();
    }

    /**
     * 更新字典类型
     *
     * @param dictionaryType
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "editDictType", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("dictionaryType") @Valid DictionaryType dictionaryType, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryTypeService.updateDictionaryType(dictionaryType);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除/恢复
     *
     * @param id
     * @param isDeleted
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/deleted/{isDeleted:\\b0\\b|\\b1\\b}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Response deleted(@PathVariable("id") Long id, @PathVariable("isDeleted") byte isDeleted) {
        DictionaryType dictionaryType = dictionaryTypeService.findDictionaryTypeById(id);
        dictionaryType.setIsDeleted(isDeleted);
        dictionaryTypeService.updateDictionaryType(dictionaryType);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Response remove(@PathVariable("id") Long id) {
        dictionaryTypeService.deleteDictionaryTypeById(id);
        return Response.getSuccessResponse();
    }

}
