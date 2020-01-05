function Itemlist(displayElement) {
    var _this = this;

    // TODO: Remove, if no longer needed.
    /*this.displayElement = displayElement;
    this.displayElement.querySelectorAll('.task-add')
        .forEach(element => element.addEventListener('click', function () {
            _this.add(element);
        }));*/

    // member variables

    this.itemListPanel = displayElement;

    this.collectionId = this.itemListPanel.dataset.collectionId;
    this.itemType = this.itemListPanel.dataset.itemType;

    this.itemListPanel.querySelectorAll('.item').forEach(element => new Item(element, _this));

    this.addItemButtonPanel = this.itemListPanel.querySelector(".add-item-button-panel");
    this.addItemButton = this.addItemButtonPanel.querySelector(".add-item-button");
    this.addItemFormPanel = this.itemListPanel.querySelector(".add-item-form-panel");
    this.addItemInputField = this.itemListPanel.querySelector(".add-item-input");
    this.addItemSaveButton = this.addItemFormPanel.querySelector(".add-item-submit");
    this.addItemCancelButton = this.addItemFormPanel.querySelector(".add-item-cancel");

    // event listeners

    this.addItemButton.addEventListener('click', showAddItemFormPanel);

    this.addItemCancelButton.addEventListener('mousedown', hideAddItemFormPanel);

    this.addItemSaveButton.addEventListener('mousedown', function () {
        if (_this.addItemInputField.value !== "") {
            postAndInsertItem();
        }
        hideAddItemFormPanel();
    });

    this.addItemInputField.addEventListener('focusout', function () {
        if (_this.addItemInputField.value === "") {
            hideAddItemFormPanel();
        }
    });

    this.addItemInputField.addEventListener('keyup', function (e) {
        if (isKeypressEnter(e)) {
            if (_this.addItemInputField.value !== "") {
                postAndInsertItem();
            }
            hideAddItemFormPanel();
        }
    });

    // private functions

    function showAddItemFormPanel() {
        _this.addItemButtonPanel.style.display = "none";
        _this.addItemFormPanel.style.display = "";

        _this.addItemInputField.value = "";
        _this.addItemInputField.focus();
    }

    function hideAddItemFormPanel() {
        _this.addItemButtonPanel.style.display = "";
        _this.addItemFormPanel.style.display = "none";
    }

    async function postAndInsertItem() {
        let addUrl = "";
        switch (_this.itemType) {
            case "task":
                addUrl = "/api/tasks";
                break;
            case "event":
                addUrl = "/api/events";
                break;
            case "note":
                addUrl = "/api/notes";
                break;
        }

        try {
            let newStatus = "TODO";

            const itemData = await httpPost(addUrl, {
                "collection": _this.collectionId,
                "title": _this.addItemInputField.value,
                "status": newStatus
            });
            //TODO: Verify response status code and only insert newTaskElement, if response was success.
            //TODO: Different HTML needed for event, task, note?
            let newItem = newItemElement(itemData.id, itemData.title, itemData.status, _this.itemType);
            _this.itemListPanel.insertBefore(newItem, _this.addItemButtonPanel);

            new Item(newItem, _this);
        } catch (error) {
            console.log(error);
        }

    }

    this.removeItem = function (item) {
        let url = "";
        switch (_this.itemType) {
            case "task":
                url = "/api/tasks";
                break;
            case "event":
                url = "/api/events";
                break;
            case "note":
                url = "/api/notes";
                break;
        }

        url += "/" + item.displayElement.dataset.itemId;

        httpDelete(url);
        //TODO: Verify response status code and only insert newTaskElement, if response was success.
        _this.itemListPanel.removeChild(item.displayElement);
    };

    function newItemElement(id, title, status, type) {
        let newItemElement = document.createElement('a');
        newItemElement.setAttribute('class', 'panel-block item');
        newItemElement.setAttribute('data-item-id', id);
        newItemElement.setAttribute('data-item-status', status);

        // TODO: Remove and replace usage with getIconClass()?
        let iconClass = "";
        switch (type) {
            case "task":
                iconClass = "fa-square";
                break;
            case "event":
                iconClass = "fa-calendar";
                break;
            case "note":
                iconClass = "fa-file";
                break;
        }

        newItemElement.innerHTML = `
        <span class="panel-icon">
            <i class="fas ${iconClass}" aria-hidden="true"></i>
        </span>
        <span class="item-title">${title}</span>
        <div class="column is-paddingless"></div>
        <div class="column is-2 is-paddingless">
            <div class="buttons has-addons is-pulled-right">
                <button class="item-action action-resolve button fas fa-check-square" title="Resolve"></button>
                <button class="item-action action-move button fas fa-caret-square-right" title="Move"></button>
                <button class="item-action action-cancel button fas fa-minus-square" title="Cancel"></button>
                <button class="item-action action-edit button fas fa-edit" title="Edit"></button>
                <button class="item-action action-delete button fas fa-trash" title="Delete"></button>
            </div>
        </div>
        </a>`;

        return newItemElement;
    }
}

// TODO: Remove, if no longer needed.
/*ItemList.prototype.add = function (element) {

};*/

function Item(displayElement, itemList) {
    this.displayElement = displayElement;
    this.itemList = itemList;
    this.parentElement = this.displayElement.parentElement;
    this.itemType = this.parentElement.dataset.itemType;
    this.itemId = this.displayElement.dataset.itemId;
    this.status = this.displayElement.dataset.itemStatus;
    this.title = this.displayElement.querySelector('.item-title').textContent;

    // TODO: Remove and replace usage with getIconClass().
    switch (this.itemType) {
        case "task":
            this.iconClass = "fa-square";
            break;
        case "event":
            this.iconClass = "fa-calendar";
            break;
        case "note":
            this.iconClass = "fa-file";
            break;
        default:
            this.iconClass = "";
    }

    var _this = this;

    this.displayElement.querySelector('.action-resolve').addEventListener('click', this.toggleStatus.bind(this), false);
    this.displayElement.querySelector('.action-edit').addEventListener('click', this.edit.bind(this), false);
    this.displayElement.querySelector('.action-delete').addEventListener('click', this.delete.bind(this), false);
}

Item.prototype.toggleStatus = async function () {
    let newStatus = this.status;
    if (this.status === "TODO") {
        newStatus = "DONE";
    } else if (this.status === "DONE") {
        newStatus = "TODO";
    }

    try {
        // TODO: Replace hardcoded status accordingly
        const itemData = await httpPost(this.getUpdateUrl(), {
            "title": this.title,
            "status": newStatus
        });
        //TODO: Verify response status code and only replace title, if response was success.
        this.status = newStatus;

    } catch (error) {
        console.log(error);
    }

    this.displayElement.querySelector('.panel-icon i').className = "fas " + this.getIconClass();
};

Item.prototype.edit = function () {
    this.displayElement.innerHTML = this.getEditableHtml();

    let editSubmit = this.displayElement.querySelector('.add-item-submit');
    let editCancel = this.displayElement.querySelector('.add-item-cancel');
    let editInput = this.displayElement.querySelector('.add-item-input');

    editInput.focus();

    editSubmit.addEventListener('click', this.editSave.bind(this), false);
    editCancel.addEventListener('click', this.editCancel.bind(this), false);

    editInput.addEventListener('focusout', (function () {
        if (editInput.value === "" || editInput.value === this.title) {
            this.editCancel();
        }
    }).bind(this), false);

    editInput.addEventListener('keyup', (function (e) {
        if (isKeypressEnter(e)) {
            if (editInput.value !== "" && editInput.value !== this.title) {
                this.editSave();
            }
            this.editCancel();
        }
    }).bind(this), false);
};

Item.prototype.editCancel = function () {
    this.displayElement.innerHTML = this.getNonEditableHtml();
    this.displayElement.querySelector('.action-edit').addEventListener('click', this.edit.bind(this), false);
    this.displayElement.querySelector('.action-delete').addEventListener('click', this.delete.bind(this), false);
};

Item.prototype.editSave = async function () {
    let newTitle = this.displayElement.querySelector('.add-item-input').value;

    let updateUrl = "";
    switch (this.itemType) {
        case "task":
            updateUrl = "/api/tasks";
            break;
        case "event":
            updateUrl = "/api/events";
            break;
        case "note":
            updateUrl = "/api/notes";
            break;
    }

    updateUrl += "/" + this.itemId;

    try {
        // TODO: Replace hardcoded status accordingly
        const itemData = await httpPost(updateUrl, {
            "title": newTitle,
            "status": "TODO"
        });
        //TODO: Verify response status code and only replace title, if response was success.
        this.title = newTitle;

    } catch (error) {
        console.log(error);
    }

    this.displayElement.innerHTML = this.getNonEditableHtml();
    this.displayElement.querySelector('.action-edit').addEventListener('click', this.edit.bind(this), false);
    this.displayElement.querySelector('.action-delete').addEventListener('click', this.delete.bind(this), false);
};

Item.prototype.delete = function () {
    this.itemList.removeItem(this);
};

Item.prototype.getIconClass = function () {
    let iconClass = "";

    switch (this.itemType) {
        case "task":
            iconClass = (this.status === "DONE") ? "fa-check-square" : "fa-square";
            break;
        case "event":
            iconClass = (this.status === "DONE") ? "fa-calendar-check" : "fa-calendar";
            break;
        case "note":
            iconClass = "fa-file";
            break;
        default:
            this.iconClass = "";
    }

    return iconClass;
};

Item.prototype.getUpdateUrl = function () {
    let updateUrl = "";
    switch (this.itemType) {
        case "task":
            updateUrl = "/api/tasks";
            break;
        case "event":
            updateUrl = "/api/events";
            break;
        case "note":
            updateUrl = "/api/notes";
            break;
    }

    updateUrl += "/" + this.itemId;

    return updateUrl;
};

Item.prototype.getEditableHtml = function () {
    let placeholderText = "";
    switch (this.itemType) {
        case "task":
            placeholderText = "New Task";
            break;
        case "event":
            placeholderText = "New Event";
            break;
        case "note":
            placeholderText = "New Note";
            break;
    }

    return `
            <input type="text" class="add-item-input input" placeholder="${placeholderText}" value="${this.title}"/>
            <button type="submit" class="add-item-submit button is-success with-margin-left">Save</button>
            <button type="reset" class="add-item-cancel button with-margin-left">Cancel</button>`
};

Item.prototype.getNonEditableHtml = function () {
    return `<span class="panel-icon">
            <i class="fas ${this.iconClass}" aria-hidden="true"></i>
        </span>
        <span class="item-title">${this.title}</span>
        <div class="column is-paddingless"></div>
        <div class="column is-2 is-paddingless">
            <div class="buttons has-addons is-pulled-right">
                <button class="item-action action-resolve button fas fa-check-square" title="Resolve"></button>
                <button class="item-action action-move button fas fa-caret-square-right" title="Move"></button>
                <button class="item-action action-cancel button fas fa-minus-square" title="Cancel"></button>
                <button class="item-action action-edit button fas fa-edit" title="Edit"></button>
                <button class="item-action action-delete button fas fa-trash" title="Delete"></button>
            </div>
        </div>`;
};
