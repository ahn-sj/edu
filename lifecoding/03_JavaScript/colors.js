  var Links = {
    setColor: function(color) {
      var aList = document.querySelectorAll('a');
      var i = 0;

      while (i < aList.length) {
        aList[i].style.color = color;
        i++;
      }
    }
  }

  var Body = {
    setColor: function(color) {
      document.querySelector('body').style.color = color;
    },
    setBackgroundColor: function(color) {
      document.querySelector('body').style.backgroundColor = color;
    }
  }

  function nightDayHandler(self) {
    var target = document.querySelector('body');

    if (self.value === 'night') {
      Body.setBackgroundColor('black');
      Body.setColor('white');
      Links.setColor('powderblue');
      self.value = 'day';
    } else {
      Body.setBackgroundColor('white');
      Body.setColor('black');
      Links.setColor('red');
      self.value = 'night';
    }
  }
