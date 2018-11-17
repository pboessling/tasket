var APP = APP || (function () {

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
                    currentlyDragging = event.target;
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
                    currentlyDragging.parentNode.removeChild(currentlyDragging);
                    column.appendChild(currentlyDragging);
                    column.classList.remove('hovering');
                    currentlyDragging = null;
                };
            });
        },


        /**
         * Initializes the application
         */
        init: function () {
            APP.registerTaskDragAndDrop();
        }
    }

})();

APP.init();