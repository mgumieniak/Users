package com.database.users.utils.templateMethodSave;

import com.database.users.utils.templateMethodSave.operations.Save;

public class SaveOperation extends Operation {
    public SaveOperation(Save save) {
        super(save::operateMapToUser,
                save::operateAddField,
                save::operateSave,
                save::operateMapToUserDTO);
    }
}


