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
    var taskAddElement = element.parentElement;

    var newTaskElement = document.createElement('p');
    newTaskElement.setAttribute('class', 'panel-block');
    var newTaskInputField = document.createElement('input');
    newTaskInputField.setAttribute('type', 'text');
    newTaskInputField.setAttribute('class', 'input');
    newTaskInputField.setAttribute('placeholder', 'New Task');
    newTaskElement.appendChild(newTaskInputField);

    var newTaskSaveButton = document.createElement('button');
    newTaskSaveButton.setAttribute('id', 'create-task-submit');
    newTaskSaveButton.setAttribute('type', 'submit');
    newTaskSaveButton.setAttribute('class', 'button is-success with-margin-left');
    newTaskSaveButton.textContent = "Save";
    newTaskElement.appendChild(newTaskSaveButton);

    var newTaskCancelButton = document.createElement('button');
    newTaskCancelButton.setAttribute('id', 'create-task-cancel');
    newTaskCancelButton.setAttribute('type', 'submit');
    newTaskCancelButton.setAttribute('class', 'button with-margin-left');
    newTaskCancelButton.textContent = "Cancel";
    newTaskElement.appendChild(newTaskCancelButton);

    //element.parentNode.parentNode.insertBefore(newTaskElement, element.parentNode);
    taskAddElement.replaceWith(newTaskElement);

    newTaskInputField.focus();

    newTaskCancelButton.addEventListener('click', function () {
        //tasklistPanel.removeChild(newTaskElement);
        newTaskElement.replaceWith(taskAddElement);
    });

    newTaskSaveButton.addEventListener('click', function () {
        if (newTaskInputField.value === "") {
            tasklistPanel.removeChild(newTaskElement);
        } else {
            // TODO: extract to method
            httpPostJson('/api/tasks', {
                "collection": tasklistPanel.dataset.collectionId,
                "title": newTaskInputField.value,
                "status": "TODO"
            });
            //TODO: verify response status code
            //newTaskElement.removeChild(newTaskInputField);
            //newTaskElement.textContent = newTaskInputField.value;

            //element.parentNode.parentNode.insertBefore(createTaskHtml(newTaskInputField.value),newTaskElement);
            newTaskElement.parentElement.appendChild(taskAddElement);
            newTaskElement.replaceWith(createTaskHtml(newTaskInputField.value));

        }
    });

    newTaskInputField.addEventListener('focusout', function () {
        if (newTaskInputField.value === "") {
            newTaskElement.parentElement.appendChild(taskAddElement);
            tasklistPanel.removeChild(newTaskElement);
        } /*else {
            // TODO: extract to method
            httpPostJson('/api/tasks', {
                "collection": tasklistPanel.dataset.collectionId,
                "title": newTaskInputField.value,
                "status": "TODO"
            });
            //TODO: verify response status code
            //newTaskElement.removeChild(newTaskInputField);
            //newTaskElement.textContent = newTaskInputField.value;
            newTaskElement.replaceWith(createTaskHtml(newTaskInputField.value));
        }*/
    });

    /**newTaskInputField.addEventListener('keyup', function (e) {
        if (isKeypressEscape(e)) {
            tasklistPanel.removeChild(newTaskElement);
        } else if (isKeypressEnter(e)) {
            if (newTaskInputField.value !== "") {
                // TODO: extract to method
                httpPostJson('/api/tasks', {
                    "collection": tasklistPanel.dataset.collectionId,
                    "title": newTaskInputField.value,
                    "status": "TODO"
                });
                //TODO: change input field to p-tag
                //newTaskElement.removeChild(newTaskInputField);
                //newTaskElement.textContent = newTaskInputField.value;
                newTaskElement.replaceWith(createTaskHtml(newTaskInputField.value));
            } else {
                tasklistPanel.removeChild(newTaskElement);
            }
        }
    });*/

    // TODO: add event listener -> keyboard enter
    // TODO: add event listener -> keyboard esacpe
};

function buildCreateTaskInput() {
    var newTaskInputElement = document.createElement('p');
    newTaskInputElement.setAttribute('class', 'panel-block task-new');
    newTaskInputElement.innerHTML = `
    <input type="text" class="input" placeholder="New Task" />
    <button id="create-task" type="submit" class="button is-success">Save</button>
    `;

    return newTaskInputElement;
}

function createTaskHtml(taskTitle) {
    var newTaskElement = document.createElement('a');
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