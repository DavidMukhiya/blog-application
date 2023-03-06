import React, { useEffect, useState } from "react";
import {
  Container,
  Card,
  CardHeader,
  CardBody,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Col,
} from "reactstrap";
import { signUp } from "../services/user-service";

const Signup = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {
    console.log(data);
  }, [data]);

  //handle change
  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
    //console.log(data)
  };

  //reseting the form
  const resetData = () => {
    setData({
      name: "",
      email: "",
      password: "",
      about: "",
    });
  };

  //submitting the form
  const submitForm = (event) => {
    event.preventDefault();
    console.log(data);
    //data validate

    //call server api for sending data
    signUp(data)
      .then((resp) => {
        console.log(resp);
        console.log("success log");
      })
      .catch((error) => {
        console.log(error);
        console.log("error log");
      });
  };

  return (
    <Container>
      <Row className="mt-4">
        {JSON.stringify(data)}
        <Col
          sm={{ size: 12, offset: 0 }}
          md={{ size: 6, offset: 3 }}
          lg={{ size: 6, offset: 3 }}
          outline="true"
        >
          <Card color="dark" inverse>
            <CardHeader>
              <h3>Fill Information to Register !!</h3>
            </CardHeader>
            <CardBody>
              <Form onSubmit={submitForm}>
                {/* Name Field */}
                <FormGroup>
                  <Label for="name">Enter Name</Label>
                  <Input
                    type="text"
                    placeholder="Enter here"
                    id="name"
                    onChange={(e) => handleChange(e, "name")}
                    value={data.name}
                  />
                </FormGroup>

                {/* Email Field */}
                <FormGroup>
                  <Label for="email">Enter Email</Label>
                  <Input
                    type="email"
                    placeholder="Enter here"
                    id="email"
                    onChange={(e) => handleChange(e, "email")}
                    value={data.email}
                  />
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for="password">Enter Password</Label>
                  <Input
                    type="password"
                    placeholder="Enter here"
                    id="password"
                    onChange={(e) => handleChange(e, "password")}
                    value={data.password}
                  />
                </FormGroup>

                {/* About Field */}
                <FormGroup>
                  <Label for="about">Enter Name</Label>
                  <Input
                    type="textarea"
                    placeholder="Enter here"
                    id="about"
                    style={{ height: "250px" }}
                    onChange={(e) => handleChange(e, "about")}
                    value={data.about}
                  />
                </FormGroup>

                <Container className="text-center">
                  <Button outline color="light">
                    Register
                  </Button>
                  <Button
                    color="secondary"
                    type="reset"
                    className="ms-2"
                    onClick={resetData}
                  >
                    Reset
                  </Button>
                </Container>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Signup;
