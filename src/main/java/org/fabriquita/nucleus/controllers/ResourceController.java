package org.fabriquita.nucleus.controllers;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.fabriquita.nucleus.models.Resource;
import org.fabriquita.nucleus.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/resource")
@Api("User Rest Services")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequiresAuthentication
    @RequiresPermissions("resource:r")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource> list() {
        return resourceService.list();
    }

    @RequiresAuthentication
    @RequiresPermissions("resource:r")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource get(@PathVariable(value = "id") Long id) {
        return resourceService.get(id);
    }

    @RequiresAuthentication
    @RequiresPermissions("resource:c")
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource add(@RequestBody Map<String, Object> data) {
        String name = null;
        Long groupId = null;
        if (data.get("name") != null) {
            name = (String) data.get("name");
        }
        if (data.get("group_id") != null) {
            groupId = new Long(data.get("group_id").toString());
        }
        return resourceService.add(name, groupId);
    }

    @RequiresAuthentication
    @RequiresPermissions("resource:u")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource update(@PathVariable(value = "id") Long id,
            @RequestBody Map<String, Object> data) {
        String name = null;
        Long groupId = null;
        if (data.get("name") != null) {
            name = (String) data.get("name");
        }
        if (data.get("group_id") != null) {
            groupId = new Long(data.get("group_id").toString());
        }
        return resourceService.update(id, name, groupId);
    }

    @RequiresAuthentication
    @RequiresPermissions("resource:d")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        resourceService.delete(id);
    }

}
