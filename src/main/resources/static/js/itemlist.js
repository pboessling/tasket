function Itemlist(displayElement) {
    var _this = this;

    // TODO: Remove, if no longer needed.
    /*this.displayElement = displayElement;
    this.displayElement.querySelectorAll('.task-add')
        .forEach(element => element.addEventListener('click', function () {
            _this.add(element);
        }));*/

    // member variables

    this.tasklistPanel = displayElement;

    this.collectionId = this.tasklistPanel.dataset.collectionId;
    this.itemType = this.tasklistPanel.dataset.itemType;
    this.addTaskButtonPanel = this.tasklistPanel.querySelector(".add-task-panel");
    this.addTaskButton = this.addTaskButtonPanel.querySelector(".add-task-button");
    this.addTaskFormPanel = this.tasklistPanel.querySelector(".add-task-form");
    this.addTaskInputField = this.tasklistPanel.querySelector(".create-task-input");
    this.addTaskSaveButton = this.addTaskFormPanel.querySelector(".create-task-submit");
    this.addTaskCancelButton = this.addTaskFormPanel.querySelector(".create-task-cancel");

    // event listeners

    this.addTaskButton.addEventListener('click', showAddTaskForm);

    this.addTaskCancelButton.addEventListener('mousedown', hideAddTaskForm);

    this.addTaskSaveButton.addEventListener('mousedown', function () {
        if (_this.addTaskInputField.value !== "") {
            postAndAddTask();
        }
        hideAddTaskForm();
    });

    this.addTaskInputField.addEventListener('focusout', function () {
        if (_this.addTaskInputField.value === "") {
            hideAddTaskForm();
        }
    });

    this.addTaskInputField.addEventListener('keyup', function (e) {
        if (isKeypressEnter(e)) {
            if (_this.addTaskInputField.value !== "") {
                postAndAddTask();
            }
            hideAddTaskForm();
        }
    });

    // private functions

    function showAddTaskForm() {
        _this.addTaskButtonPanel.style.display = "none";
        _this.addTaskFormPanel.style.display = "";

        _this.addTaskInputField.value = "";
        _this.addTaskInputField.focus();
    }

    function hideAddTaskForm() {
        _this.addTaskButtonPanel.style.display = "";
        _this.addTaskFormPanel.style.display = "none";
    }

    function postAndAddTask() {
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
            "title": _this.addTaskInputField.value,
            "status": "TODO"
        });
        //TODO: Verify response status code and only insert newTaskElement, if response was success.
        _this.tasklistPanel.insertBefore(newTaskElement(_this.addTaskInputField.value), _this.addTaskButtonPanel);
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