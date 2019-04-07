//import {initSortable}    from './sortable.js';

var APP = APP || (function () {

    var tasksApiUrl = '/api/tasks';

    return {
        /**
         * Registers the drag & drop of tasks.
         */
        registerTaskDragAndDrop: function () {
            document.querySelectorAll('.task').forEach(function (task) {
                //let currentlyDragging = null;

                task.setAttribute('draggable', true);
                task.ondragstart = function (event) {
                    event.dataTransfer.effectAllowed = 'move';
                    event.dataTransfer.setData('text/html', this.innerHTML);
                    currentlyDragging = task;
                    //currentlyDragging.classList.remove('is-white');
                    //currentlyDragging.classList.add('is-danger');
                }
            });

            document.querySelectorAll('.drop-zone').forEach(function (column) {
                column.ondragenter = column.ondragover = function (event) {
                    event.preventDefault();
                    column.classList.add('hovering');
                };

                column.ondragleave = function () {
                    column.classList.remove('hovering');
                };

                column.ondrop = function (event) {
                    var taskId = currentlyDragging.dataset.taskId;
                    var title = currentlyDragging.querySelector('span').textContent;
                    var oldStatus = currentlyDragging.parentNode.dataset.taskStatus;
                    var newStatus = column.dataset.taskStatus;

                    console.debug('Moved task ' + taskId + ' from status ' + oldStatus + ' to status ' + newStatus);

                    currentlyDragging.parentNode.removeChild(currentlyDragging);
                    column.appendChild(currentlyDragging);
                    column.classList.remove('hovering');
                    currentlyDragging = null;

                    var url = tasksApiUrl + '/' + taskId;
                    var data = {
                        id: taskId,
                        title: title,
                        status: newStatus
                    };

                    APP.updateBackendTaskStatus(url, data);
                };
            });
        },

        updateBackendTaskStatus: function (url, data) {
            fetch(url, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                body: JSON.stringify(data)
            }).then(response => {
                return response.json();
            }).then(data => {
                console.debug(data);
            }).catch(error => {
                console.error(error);
            });
        },

        /**
         * Initializes the application
         */
        init: function () {
            APP.registerTaskDragAndDrop();
            //initSortable('.sortable-list');
            var timer = new Timer(25 * 60, document.querySelector('#timer'));
            document.querySelector('#timer-start-button').addEventListener('click', function () {
                timer.start();
            });
            document.querySelector('#timer-stop-button').addEventListener('click', function () {
                timer.stop();
            });
            document.querySelector('#timer-reset-button').addEventListener('click', function () {
                timer.reset();
            });

            //init tasklist
            document.querySelectorAll('.task-list').forEach(element => new TaskList(element));
            document.querySelectorAll('.task-new').forEach(element => new Task(element));
        }
    }

})();

window.onload = function () {
    APP.init();
};

