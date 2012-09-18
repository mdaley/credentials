# credentials

A little learning experience for me.
Maintains user credentials and allows
the validation of a user's credentials.

## Usage

Who knows, might be useful one day.

/status - get status of the service.
/ping - ping the service.
/roles - roles that users can be in
  POST - create a new role

POST new role:

{
  "id":"descriptive id for the role"
}

response:

{
  "id":"descriptive id of the role"
  "type": "role"
  "status":201
}

## License

Copyright © 2012 Matthew Daley

Distributed under the Eclipse Public License, the same as Clojure.
