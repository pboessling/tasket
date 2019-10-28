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
            const itemData = await httpPost(addUrl, {
                "collection": _this.collectionId,
                "title": _this.addItemInputField.value,
                "status": "TODO"
            });
            //TODO: Verify response status code and only insert newTaskElement, if response was success.
            //TODO: Different HTML needed for event, task, note?
            let newItem = newItemElement(itemData.id, itemData.title, _this.itemType);
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

    function newItemElement(id, title, type) {
        let newItemElement = document.createElement('a');
        newItemElement.setAttribute('class', 'panel-block item');
        newItemElement.setAttribute('data-item-id', id);

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
        <span>${title}</span>
        <div class="column is-paddingless"></div>
        <div class="column is-2 is-paddingless">
            <div class="buttons has-addons is-pulled-right">
                <button class="item-action button fas fa-check-square" title="Resolve"></button>
                <button class="item-action button fas fa-caret-square-right" title="Move"></button>
                <button class="item-action button fas fa-minus-square" title="Cancel"></button>
                <button class="item-action button fas fa-edit" title="Edit"></button>
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

    var _this = this;

    this.displayElement.querySelectorAll('.action-delete')
        .forEach(element => element.addEventListener('click', function () {
            //_this.delete();
            _this.itemList.removeItem(_this);
        }));
}

Item.prototype.edit = function () {
    console.log('edit called');
    // TODO: change element into an input field
};

Item.prototype.save = function () {
    console.log('save called');
    // TODO: change element back into text and send request to backend to save changed task
};

Item.prototype.delete = function () {
    //this.parentElement.removeChild(this.displayElement);
    // TODO: send request to backend to delete task
};
