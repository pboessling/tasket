// TODO: Clean up code.
// TODO: Add documentation.
function Timer(duration, displayElement) {
    this.duration = duration;
    this.displayElement = displayElement;

    this.diff = 0;

    this.running = false;
    this.scheduler = null;
}

Timer.prototype.start = function () {
    if (this.running) {
        return;
    }
    this.running = true;

    var startDate = Date.now(),
        that = this,
        obj;

    var timerStartValue;
    if (that.diff > 0) {
        timerStartValue = that.diff;
    } else {
        timerStartValue = that.duration;
    }

    function timer() {
        that.diff = timerStartValue - (((Date.now() - startDate) / 1000) | 0);

        if (that.diff <= 0) {
            that.stop();
            //navigator.vibrate(1000);
            Timer.notify('Time is up!');
        }

        obj = Timer.parse(that.diff);
        that.displayElement.textContent = obj.minutes + ":" + obj.seconds;
    }

    this.scheduler = setInterval(timer, 1000);
};

Timer.prototype.stop = function () {
    this.running = false;
    clearInterval(this.scheduler);
};

Timer.prototype.reset = function () {
    stop();
    this.diff = 0;

    var obj = Timer.parse(this.duration);
    this.displayElement.textContent = obj.minutes + ":" + obj.seconds;
};

Timer.parse = function (diff) {
    let minutes = (diff / 60) | 0;
    let seconds = (diff % 60) | 0;

    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    return {
        'minutes': minutes,
        'seconds': seconds
    };
};

Timer.notify = function (message) {
    if (!("Notification" in window)) {
        return;
    } else if (Notification.permission === "granted") {
        var notification = new Notification(message);
    } else if (Notification.permission !== 'denied') {
        Notification.requestPermission(function (permission) {
            if (permission === "granted") {
                var notification = new Notification(message);
            }
        });
    }
};