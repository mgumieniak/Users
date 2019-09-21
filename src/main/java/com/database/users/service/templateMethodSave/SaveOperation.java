package com.database.users.service.templateMethodSave;

import com.database.users.service.templateMethodSave.operations.Save;

public class SaveOperation extends Operation {
    public SaveOperation(Save save) {
        super(save::operateMapToUser,
                save::operateAddField,
                save::operateSave,
                save::operateMapToUserDTO);
    }
}


