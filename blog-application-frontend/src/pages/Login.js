import React, { useState } from "react";
import { toast } from "react-toastify";
import {
  Container,
  Row,
  Col,
  Card,
  CardHeader,
  CardBody,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
} from "reactstrap";
import { doLogin } from "../auth";
import { loginUser } from "../services/user-service";

const Login = () => {
  const [loginDetail, setLoginDetail] = useState({
    username: "",
    password: "",
  });

  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualValue,
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);

    //validation
    if (
      loginDetail.username.trim() === "" ||
      loginDetail.password.trim() === ""
    ) {
      toast.error("Username or Password is required !!");
      return;
    }

    //submit the data to server to generate token
    loginUser(loginDetail)
      .then((data) => {
        console.log("user login:");
        console.log(data);

        //save the data to localstorage
        doLogin(data, ()=>{
          console.log("Login detail is save to local storage")
          //redirect to user dashboard page
        })

        toast.success("Login Success");
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status === 400 || error.response.status === 404) {
          toast.error(error.response.data.message);
        } else {
          toast.error("Something went wrong on server !!");
        }
      });
  };
  const handleReset = () => {
    setLoginDetail({
      username: "",
      password: "",
    });
  };

  return (
    <Container>
      <Row className="mt-4">
        <Col lg={{ size: 6, offset: 3 }}>
          <Card color="dark" inverse>
            <CardHeader>
              <h3>Login Here !!</h3>
            </CardHeader>
            <CardBody>
              <Form onSubmit={handleFormSubmit}>
                {/* Email Field */}
                <FormGroup>
                  <Label for="email">Enter Email</Label>
                  <Input
                    type="text"
                    id="email"
                    value={loginDetail.username}
                    onChange={(e) => handleChange(e, "username")}
                  />
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for="password">Enter Password</Label>
                  <Input
                    type="password"
                    id="password"
                    value={loginDetail.password}
                    onChange={(e) => handleChange(e, "password")}
                  />
                </FormGroup>

                <Container className="text-center">
                  <Button outline color="light">
                    Login
                  </Button>
                  <Button
                    onClick={handleReset}
                    className="ms-2"
                    color="secondary"
                    type="reset"
                    outline
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

export default Login;
