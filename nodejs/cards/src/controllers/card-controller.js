const neDB = require("../configurations/database");
const api = {};

api.findAll = (request, response) => {
  neDB.find({}).exec((err, cards) => {
    if (err) {
      const msg = "Cannot list all cards!";
      console.error(msg, err);

      response.status(err.status | 400);
      response.json({ mensagem: msg });
    }

    response.json(cards);
  });
};

api.save = (request, response) => {
  const canonical = request.body;
  neDB.insert(canonical, (err, card) => {
    if (err) {
      const msg = "Cannot save current card.";
      console.error(msg, err);
      response.status(err.status | 400);
      response.json({ mensagem: msg });
    }

    response.status(201);
    response.json(card);
  });
};

api.updateById = (request, response) => {
  const { id } = request.params;

  neDB.update({ _id: id }, request.body, {}, (err, numReplaced) => {
    if (err) {
      const msg = "Error upon update card informations!";
      console.error(msg, err);

      response.status(err.status | 400);
      response.json({ mensagem: msg });
    } else if (numReplaced != 0) response.json({ mensagem: "Updated Card!" });
  });
};

api.deleteById = (request, response) => {
  const { id } = request.params;

  neDB.remove({ _id: id }, {}, function (err, numRemoved) {
    if (err) {
      const msg = "Error upon delete current card!";
      console.error(msg, err);

      response.status(err.status | 400);
      response.json({ mensagem: msg });
    } else if (numRemoved != 0) response.json({ mensagem: "Removed Card!" });
  });
};

api.findById = (request, response) => {
  const { id } = request.params;

  neDB.find({ _id: id }, {}, function (err, card) {
    if (err) {
      const msg = "Error upon find current card!";
      console.error(msg, err);

      response.status(err.status | 400);
      response.json({ mensagem: msg });
    }

    response.json(card);
  });
};


api.paginationAndSorting = (request,response) =>{
    const {name} = request.params;
    // const {limit} = request.params;

    neDB.find({}).sort({ customerName: 1 }).skip(1).limit(5).exec(function (err, docs) {
    if (err) {
      const msg = "Error upon find current cards!";
      console.error(msg, err);

      response.status(err.status | 400);
      response.json({ mensagem: msg });
    }
     response.json(docs);
  });
}

module.exports = api;