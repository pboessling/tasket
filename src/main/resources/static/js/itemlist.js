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

    function postAndInsertItem() {
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

        httpPostJson(addUrl, {
            "collection": _this.collectionId,
            "title": _this.addItemInputField.value,
            "status": "TODO"
        });
        //TODO: Verify response status code and only insert newTaskElement, if response was success.
        //TODO: Different HTML needed for event, task, note?
        _this.itemListPanel.insertBefore(newTaskElement(_this.addItemInputField.value), _this.addItemButtonPanel);
    }

    function newTaskElement(taskTitle) {
        let newTaskElement = document.createElement('a');
        newTaskElement.setAttribute('class', 'panel-block task-new');
        newTaskElement.innerHTML = `
        <span class="panel-icon">
            <i class="fas fa-square" aria-hidden="true"></i>
        </span>
        <span>${taskTitle}</span>
        <div class="column is-paddingless"></div>
        <div class="column is-2 is-paddingless">
            <div class="buttons has-addons is-pulled-right">
                <button class="item-action button fas fa-check-square"></button>
                <button class="item-action button fas fa-caret-square-right"></button>
                <button class="item-action button fas fa-minus-square"></button>
                <button class="item-action button fas fa-edit"></button>
                <button class="item-action task-delete button fas fa-trash"></button>
            </div>
        </div>
        </a>`;

        return newTaskElement;
    }
}

// TODO: Remove, if no longer needed.
/*TaskList.prototype.add = function (element) {

};*/

function Task(displayElement) {
    this.displayElement = displayElement;

    var _this = this;

    this.displayElement.querySelectorAll('.task-delete')
        .forEach(element => element.addEventListener('click', function () {
            _this.delete();
        }));
}

Task.prototype.edit = function () {
    console.log('edit called');
    // TODO: change element into an input field
};

Task.prototype.save = function () {
    console.log('save called');
    // TODO: change element back into text and send request to backend to save changed task
};

Task.prototype.delete = function () {
    this.displayElement.parentElement.removeChild(this.displayElement);
    // TODO: send request to backend to delete task
};