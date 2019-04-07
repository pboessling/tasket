function TaskList(displayElement) {
    this.displayElement = displayElement;

    var _this = this;

    this.displayElement.querySelectorAll('.task-add')
        .forEach(element => element.addEventListener('click', function () {
            _this.add(element);
        }));
}

// TODO: Refactor and clean up this code.
TaskList.prototype.add = function (element) {
    var tasklistPanel = element.parentElement.parentElement;
    var newTaskElement = document.createElement('p');
    newTaskElement.setAttribute('class', 'panel-block task-new');
    var newTaskInputField = document.createElement('input');
    newTaskInputField.setAttribute('type', 'text');
    newTaskInputField.setAttribute('class', 'input');
    newTaskInputField.setAttribute('placeholder', 'New Task');

    newTaskElement.appendChild(newTaskInputField);
    element.parentNode.parentNode.insertBefore(newTaskElement, element.parentNode);
    newTaskInputField.focus();

    newTaskInputField.addEventListener('focusout', function () {
        if (newTaskInputField.value === "") {
            tasklistPanel.removeChild(newTaskElement);
        } else {
            // TODO: extract to method
            // TODO: set status according to tasklist
            httpPostJson('/api/tasks', {
                "title": newTaskInputField.value,
                "status": "PLANNED"
            });
            //TODO: change input field to p-tag
            newTaskElement.text = newTaskInputField.value;
        }
    });

    newTaskInputField.addEventListener('keyup', function (e) {
        if (isKeypressEscape(e)) {
            tasklistPanel.removeChild(newTaskElement);
        } else if (isKeypressEnter(e)) {
            if (newTaskInputField.value !== "") {
                // TODO: extract to method
                // TODO: set status according to tasklist
                httpPostJson('/api/tasks', {
                    "title": newTaskInputField.value,
                    "status": "PLANNED"
                });
                //TODO: change input field to p-tag
                newTaskElement.text = newTaskInputField.value;
            } else {
                tasklistPanel.removeChild(newTaskElement);
            }
        }
    });

    // TODO: add event listener -> keyboard enter
    // TODO: add event listener -> keyboard esacpe
};

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